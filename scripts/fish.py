"""Convert game-data/fish.toml → fish_data.json."""

import sys
import tomllib
from pathlib import Path

from _lib.common import raw_path, write_json

FIELDS_WHERE_FALSE_MEANS_ALL = frozenset(
    {"seasons", "hours", "weather", "locations", "perk_artifact", "has_perk"}
)
FIELDS_NORMALIZE_TO_ARRAY = frozenset({"water_type", "retrieval"})


def _resolve(toml_data: dict) -> list[dict]:
    defaults = toml_data.get("default", {})
    result = []
    for key, entry in toml_data.items():
        if key == "default":
            continue
        merged = dict(defaults)
        merged |= entry

        if merged.get("item") in (None, "<..>"):
            merged["item"] = key

        for field in FIELDS_WHERE_FALSE_MEANS_ALL:
            if merged.get(field) is False:
                merged[field] = None

        for field in FIELDS_NORMALIZE_TO_ARRAY:
            val = merged.get(field)
            if isinstance(val, str):
                merged[field] = [val]

        if merged.get("any_size") is True:
            merged["size"] = "any"
        elif merged.get("size") is None or merged.get("size") is False:
            merged["size"] = "small"

        merged["name"] = key
        result.append(merged)
    return result


def convert(root: Path) -> None:
    input_path = root / "game-data" / "fish.toml"

    if not input_path.exists():
        print(
            f"Error: {input_path} not found.\n"
            "Place fish.toml in the game-data/ directory and try again.",
            file=sys.stderr,
        )
        sys.exit(1)

    with open(input_path, "rb") as f:
        data = tomllib.load(f)

    fish = _resolve(data)
    write_json(fish, raw_path(root, "fish_data.json"), "fish entries")

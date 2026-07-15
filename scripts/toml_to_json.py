#!/usr/bin/env python3
"""Convert fish.toml to fish_data.json for the Mistria app.

Usage:
    python3 scripts/toml_to_json.py

Reads game-data/fish.toml, resolves defaults, normalizes fields,
and writes a JSON array to core/data/src/main/res/raw/fish_data.json.
"""

import json
import sys
import tomllib
from pathlib import Path

FIELDS_WHERE_FALSE_MEANS_ALL = frozenset(
    {"seasons", "hours", "weather", "locations", "perk_artifact", "has_perk"}
)
FIELDS_NORMALIZE_TO_ARRAY = frozenset({"water_type", "retrieval"})


def resolve(toml_data: dict) -> list[dict]:
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


def main() -> None:
    script_dir = Path(__file__).resolve().parent
    project_root = script_dir.parent
    input_path = project_root / "game-data" / "fish.toml"
    output_path = (
        project_root
        / "core"
        / "data"
        / "src"
        / "main"
        / "res"
        / "raw"
        / "fish_data.json"
    )

    if not input_path.exists():
        print(
            f"Error: {input_path} not found.\n"
            "Place fish.toml in the game-data/ directory and try again.",
            file=sys.stderr,
        )
        sys.exit(1)

    with open(input_path, "rb") as f:
        data = tomllib.load(f)

    fish = resolve(data)

    output_path.parent.mkdir(parents=True, exist_ok=True)
    with open(output_path, "w") as f:
        json.dump(fish, f, indent=2)
        f.write("\n")

    print(f"OK — wrote {len(fish)} fish entries to {output_path}")


if __name__ == "__main__":
    main()

"""Convert game-data/npcs/*.toml → villager_data.json."""

import sys
import tomllib
from pathlib import Path

from _lib.common import raw_path, write_json


def _parse(filepath: Path) -> dict:
    with open(filepath, "rb") as f:
        data = tomllib.load(f)

    birthday = data["birthday"]
    return {
        "name": data["name"],
        "birthday_season": birthday["season"],
        "birthday_day": birthday["day"],
        "job": data["job"],
        "dateable": data["dateable"],
        "loved_gifts": data["loved_gifts"],
        "liked_gifts": data["liked_gifts"],
        "hated_gift": data["hated_gift"],
        "disliked_gift_tags": data["disliked_gift_tags"],
    }


def convert(root: Path) -> None:
    input_dir = root / "game-data" / "npcs"

    if not input_dir.is_dir():
        print(
            f"Error: {input_dir} not found.\n"
            "Place NPC TOML files in game-data/npcs/ and try again.",
            file=sys.stderr,
        )
        sys.exit(1)

    toml_files = sorted(input_dir.glob("*.toml"))
    if not toml_files:
        print(f"Error: no .toml files found in {input_dir}", file=sys.stderr)
        sys.exit(1)

    villagers = [_parse(f) for f in toml_files]
    write_json(villagers, raw_path(root, "villager_data.json"), "villagers")

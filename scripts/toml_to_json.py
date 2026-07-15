#!/usr/bin/env python3
"""Convert game TOML data files to JSON for the Mistria app.

Usage:
    python3 scripts/toml_to_json.py

Converts all TOML sources under game-data/ to JSON resource files under
core/data/src/main/res/raw/.
"""

from _lib.common import project_root
from fish import convert as convert_fish
from npcs import convert as convert_npcs


def main() -> None:
    root = project_root()
    convert_fish(root)
    convert_npcs(root)


if __name__ == "__main__":
    main()

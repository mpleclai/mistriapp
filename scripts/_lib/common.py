"""Shared utilities for toml_to_json conversion scripts."""

import json
from pathlib import Path


def project_root() -> Path:
    return Path(__file__).resolve().parents[2]


def raw_path(root: Path, filename: str) -> Path:
    return root / "core" / "data" / "src" / "main" / "res" / "raw" / filename


def write_json(data: list[dict], path: Path, label: str) -> None:
    path.parent.mkdir(parents=True, exist_ok=True)
    with open(path, "w") as f:
        json.dump(data, f, indent=2)
        f.write("\n")
    print(f"OK — wrote {len(data)} {label} to {path}")

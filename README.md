# ForgedFabric

The goal of this project is to enable porting fabric mods to forge with as few changes to their codebase as possible. This would make it easy for existing fabric mods to maintain a forge version without manually porting every update or rewriting to use a different api that provides a platform abstraction layer.

ForgedFabric is an implementation of the Fabric API on Forge. Same idea as [QuiltMC/quilted-fabric-api](https://github.com/QuiltMC/quilted-fabric-api). It's currently in the early very stages of development. I'm only implementing parts as they're used by mods that I'm working on. Eventually it should transition to being a fork of [FabricMC/fabric](https://github.com/FabricMC/fabric) and trying to maintain parity with the full fabric api.

**See [wiki](https://github.com/LukeGrahamLandry/ForgedFabric/wiki) for more information.**

## Licensing

- ForgedFabric is Copyright 2022 LukeGrahamLandry, available under the LGPL License.
- ForgedFabric uses the same package structure and method names as the Fabric API, Copyright (c) 2016, 2017, 2018, 2019 FabricMC under the [Apache License 2.0](https://github.com/FabricMC/fabric/blob/1.19.2/LICENSE). 
- ForgedFabric includes ZsoltMolnarrr/TinyConfig, v2.1.0, Copyright (c) 2022 under the [MIT License](https://github.com/ZsoltMolnarrr/TinyConfig/blob/73780c6b2603a97ed2e3227874fb171cd742dad5/LICENSE).

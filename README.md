# Forged Fabric API

ForgedFabric is a reimplementation of the Fabric API on Forge. Same idea as [QuiltMC/quilted-fabric-api](https://github.com/QuiltMC/quilted-fabric-api). 
Useful in porting mods from fabric to forge and developing multiplatform mods in general. 
It's currently in the early very stages of development. I'm only implementing parts as they're used by mods that I'm working on. 
Eventually it should transition to being a fork of [FabricMC/fabric](https://github.com/FabricMC/fabric) and trying to maintain parity with the full fabric api.  

See [LukeGrahamLandry/BetterCombat$separate-forgedfabric](https://github.com/LukeGrahamLandry/BetterCombat/tree/separate-forgedfabric) 
for an example of a mod that uses architectury-loom to build for forge and fabric from an almost unchanged fabric codebase. 

## Structure

Any parts of the fabric api that are used must be duplicated in this library.
The api classes must have exactly the same names, methods and package structure.
Then their functionality is reimplemented using forge events or mixins.

The signature of methods and fields must match the fabric api exactly to avoid NoSuchFieldError/NoSuchMethodError.
For example, it is not enough for `ServerPlayConnectionEvents#register` to accept a `TriConsumer<ServerPlayNetworkHandler, PacketSender, MinecraftServer>`,
it must take a `Join` which is a functional interface that exposes a `onPlayReady` method corresponding to that consumer.
Also, interfaces must be in the same package/class as they are in the fabric api. In that example, the `PackeSender` must be an inner class since it will not be found if it is its own file in the package.
Make sure the return types of methods are the same! Even though the result is never used by the mod, `ServerPlayNetworking#registerGlobalReceiver` must return a boolean or the method will not be recognised. 

ForgedFabric may be added to your project as a dependency. Take care that event handlers get registered properly when shadowed. 

## Problems 

- mods adding mixins to places where forge already has a patch are a bit scary 
- forge registry shenanigans. perhaps terrible solution: mixin to `Registry#register` that redirects to deferred registers based on the mod id of the identifier  
- other multiplatform libraries used from common code need to have exactly the same method signatures for everything you use 
- not sure about loading mixins and event annotations when shadowed. perhaps call `Mixins.addConfiguration("forgedfabric.mixins.json");` from other mod's constructor. should have a `EventsRegistry#register` that makes sure everything is set up

## Licensing

- The structure of the Fabric API is under the [Apache License 2.0](https://github.com/FabricMC/fabric/blob/1.19.2/LICENSE). 

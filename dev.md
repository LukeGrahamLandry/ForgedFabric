# Solutions to problems during development

### NoSuchFieldError/NoSuchMethodError on fabric api classes

> They must be reimplemented in ForgedFabric with exactly the same signature. That's what the VM cares about

### ModelPredicateProviderRegistry patch

Forge renames one version of the register method to registerGeneric.

> Just implement `FabricModelPredicateProviderRegistry` and make mods use that instead of the vanilla version

### Architectury incompatibility

Anything that uses Architectury ExpectPlatform is incompatible because it detects the loader by seeing if it can find `net.fabricmc.loader.api.FabricLoader` so it tries to load whatever.fabric.WhateverImpl but that check happens in a generated class so it doesnt depend on their api but I can't mixin to every mod's version of `architectury_inject_FTBQuests1165_common_49eeee4dc6e446f598ef468ed50261c9.PlatformMethods#getModLoader` and anyway that hash is different every version. Only thing I can think of is just don't expose that class and use something else to detect loaded mods

I'm proud of this because I set out to mimic fabric so its mods could run on forge with as few changes as possible and accidentally tricked one of the most popular systems for developing multi-platform mods into thinking it really was running on fabric.

> I just don't provide FabricLoader but this removes the way that mods check what other mods are loaded by mod id. 


# Experiments

## Using built fabric mod as a library for common code

Ideally I wouldn't even have to recompile th

Forge and fabric use different intermediary mappings for class/package names in their production jars. Forge uses official mappings and fabric uses intermediary like class_2134. So it just totally doesn't work when the code references vanilla classes at all. Also affects mixin refmaps but those could reasonably generated from the fabric ones. 

Perhaps this limitation is a positive for licensing concerns. Since creating a forge port will require access to the original mod's source code and the ability to setup a environment which is a nice little impediment for people stealing things. Otherwise i'd have to implement a license checker that reads the fabric.mod.json. 

## Intercept calls to vanilla registries with a mixin

Forge locks the registries at certain times so what if when the fabric code made a call to Registry#register, a mixin intercepted that and redirected it to a forge DeferredRegister if it was called to early. Create the DeferredRegister dynamically with the mod id from the Identifier argument. 

- when am i confident that all the DeferredRegisters that will be created are ready is also after all the mod's initializers have had a chance to register their things
- what is the method signature of a method with a generic parameter with no bound? when i was testing this my mixins weren't running properly for other reasons so i should take another look 
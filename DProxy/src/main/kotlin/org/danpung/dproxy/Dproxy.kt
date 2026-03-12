package org.danpung.dproxy;

import com.google.inject.Inject
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.player.PlayerChooseInitialServerEvent
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent
import com.velocitypowered.api.event.proxy.server.ServerRegisteredEvent
import com.velocitypowered.api.event.proxy.server.ServerUnregisteredEvent
import com.velocitypowered.api.plugin.Plugin
import com.velocitypowered.api.proxy.ProxyServer
import com.velocitypowered.api.proxy.server.RegisteredServer
import com.velocitypowered.api.proxy.server.ServerInfo
import org.slf4j.Logger
import java.net.InetSocketAddress

@Plugin(id = "dproxy", name = "Dproxy", version = BuildConstants.VERSION)
class Dproxy @Inject constructor(
    val proxy: ProxyServer,
    val logger: Logger) {


    init{
        //proxy.
    }

    @Subscribe
    fun onProxyInitialization(e: ProxyInitializeEvent) {

    }


    @Subscribe
    fun onPlayerConnected(e : PlayerChooseInitialServerEvent){
        //e.player.createConnectionRequest(serverList[0]);
        logger.info(e.initialServer.get().serverInfo.name);
    }


}


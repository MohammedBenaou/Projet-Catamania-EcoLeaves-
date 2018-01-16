/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlr.elasticsearch;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

/**
 * Classe de connexion au serveur ElasticSearch
 *
 * @author Thomas Chambon, Grégory Daly
 */
public class ElasticSearchConnexion {

    private static ElasticSearchDriver els;
    private static TransportClient transportClient;

    /**
     * Constructeur ElasticSearchConnexion
     *
     * @param host Adresse du serveur ElasticSearch (ex : 127.0.0.1)
     * @param nodePort Numéro du port du serveur ElasticSearch
     * @throws UnknownHostException
     */
    public ElasticSearchConnexion(InetAddress host, int nodePort) throws UnknownHostException {
        transportClient = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new TransportAddress(InetAddress.getLoopbackAddress(), nodePort));

    }

    /**
     * Fonction de renvoi d'instance de connexion.
     *
     * @param host Adresse du serveur ElasticSearch (ex : 127.0.0.1)
     * @param nodePort Numéro du port du serveur ElasticSearch
     * @return Instance de connexion de type ElasticSearchConnexion
     * @throws UnknownHostException
     */
    public static ElasticSearchConnexion getInstance(InetAddress host, int nodePort) throws UnknownHostException {
        if (els == null) {
            return new ElasticSearchConnexion(host, nodePort);
        } else {
            transportClient.close();
            return new ElasticSearchConnexion(host, nodePort);
        }

    }

    /**
     * Fonction de renvoi du client courant.
     *
     * @return Instance de type TransportClient
     * @throws UnknownHostException
     */
    public static TransportClient getClient() throws UnknownHostException {
        return transportClient;
    }

}

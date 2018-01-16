/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlr.elasticsearch;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.json.JSONObject;

/**
 * Classe de création de l'interface d'échange avec ElasticSearch.
 *
 * @author Thomas Chambon, Grégory Daly
 */
public class ElasticSearchDriver {

    private ElasticSearchConnexion client;

    /**
     * Constructeur ElasticSearchDriver
     *
     * @param host Adresse du serveur ElasticSearch (ex : 127.0.0.1)
     * @param nodePort Numéro du port du serveur ElasticSearch
     * @throws UnknownHostException
     */
    public ElasticSearchDriver(InetAddress host, int nodePort) throws UnknownHostException {
        this.client = ElasticSearchConnexion.getInstance(host, nodePort);
    }

    /**
     * Fonction d'indexation d'un document.
     *
     * @param index Index sur lequel le document va être indexé
     * @param type Type de document à indexer
     * @param jsonData Corps du document à indexer de type JSON (uniquement)
     * @return Affichage de l'élément indexé
     */
    public JSONObject index(String index, String type, String jsonData) {
        try {
            IndexResponse response = ElasticSearchConnexion.getClient()
                    .prepareIndex(
                            index,
                            type
                    )
                    .setSource(jsonData, XContentType.JSON)
                    .get();
            System.out.println(jsonData);
            return new JSONObject(response.toString());
        } catch (Exception ex) {
            return this.buildErrorMessage(ex);
        }
    }

    /**
     * Fonction de recherche d'un document par son id.
     *
     * @param index Index sur lequel le document va être rechercher
     * @param type Type de document à rechercher
     * @param id Id du document à rechercher
     * @return Affichage de l' élément correspondant à la recherche
     */
    public JSONObject get(String index, String type, String id) {

        try {
            IndexResponse response = ElasticSearchConnexion.getClient()
                    .prepareIndex(
                            index,
                            type,
                            id
                    )
                    .get();

            return new JSONObject(response.toString());
        } catch (Exception ex) {
            return this.buildErrorMessage(ex);
        }

    }

    /**
     * Fonction de suppression d'un document.
     *
     * @param index Index sur lequel le document va être rechercher
     * @param type Type de document à rechercher
     * @param id Id du document à rechercher
     * @return Affichage de l'élément supprimé
     */
    public JSONObject delete(String index, String type, String id) {

        try {
            DeleteResponse response = ElasticSearchConnexion.getClient()
                    .prepareDelete(
                            index,
                            type,
                            id
                    )
                    .get();

            return new JSONObject(response.toString());
        } catch (Exception ex) {
            return this.buildErrorMessage(ex);
        }

    }

    /**
     * Fonction de recherche de document.
     *
     * @param index Index sur lequel le document va être rechercher
     * @param type Type de document à rechercher
     * @param field Champ du document sur lequel la recherche va s'appliquer
     * @param query Requête avec laquelle la recherche doit correspondre
     * @param fieldSort Champ du document sur lequel va s'appliquer le tri
     * @return Affichage du ou des éléments correspondant à la recherche
     */
    public JSONObject search(String index, String type, String field, String query, String fieldSort) {
        try {

            SearchRequestBuilder searchRequestBuilder = ElasticSearchConnexion.getClient().prepareSearch()
                    .setIndices(index)
                    .setTypes(type)
                    .setQuery(QueryBuilders.wildcardQuery(field, "*" + query + "*"))
                    //.setFrom(0)
                    .setSize(9999) //.setExplain(true)
                    //.addSort(SortBuilders.fieldSort(field).order(SortOrder.DESC))
                    ;

            SearchResponse response = searchRequestBuilder.execute().actionGet();

            System.out.println(response.toString());
            return new JSONObject(response.toString());
        } catch (Exception ex) {
            return this.buildErrorMessage(ex);
        }
    }

    /**
     * Fonction de gestion d'erreur
     *
     * @param ex Exception à gérer
     * @return Message d'erreur du type {err : exception}
     */
    private JSONObject buildErrorMessage(Exception ex) {

        return new JSONObject("{\"err\" : \"" + ex + "\"}");

    }

}

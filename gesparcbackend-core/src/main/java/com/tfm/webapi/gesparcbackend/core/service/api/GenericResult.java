package com.tfm.webapi.gesparcbackend.core.service.api;

import java.util.Collection;

public interface GenericResult {
	
	/**
	 * @return La liste des erreurs trouvées s'il y en a.
	 */
	Collection<String> getErrors();
	
	/**
	 * @return La liste des avertissements trouvés s'il y en a.
	 */
	Collection<String> getWarnings();
	
	/**
	 * @return La liste d'informations liées au contrôle.
	 */
	Collection<String> getInfos();
	
	/**
	 * /**
	 *
	 * @return <code>true</code> si la vérification est bonne, <code>false</code> sinon. 
	 */
	default boolean isOK() {
		return getErrors() == null || getErrors().isEmpty();
	}
	
	/**
	 * Permet d'aggréger les résultats passés en paramètres avec le résultat de cet objet.
	 * 
	 * @param result
	 */
	default void aggregateResults(final GenericResult result) {
		if (result != null) {
			if (result.getErrors() != null) {
				getErrors().addAll(result.getErrors());
			}
			if (result.getWarnings() != null) {
				getWarnings().addAll(result.getWarnings());
			}
			if (result.getInfos() != null) {
				getInfos().addAll(result.getInfos());
			}
		}
	}

}

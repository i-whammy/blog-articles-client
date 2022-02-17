(ns blog-articles-client.core
  (:require [clj-http.client :as http]))

(defn execute []
  (let [companies (-> (http/get "http://localhost:3000/companies"
                                {:as :json})
                      (get-in [:body :companies]))]
    (map #(http/put "http://localhost:3000/articles"
                    {:query-params {:company %}}) companies)))
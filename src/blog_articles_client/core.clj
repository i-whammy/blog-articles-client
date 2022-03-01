(ns blog-articles-client.core
  (:require [clj-http.client :as http]))

(defn put-company [company]
  (try
    (http/put "http://localhost:3000/articles"
                    {:query-params {:company company}})
    (catch Exception _
           (println (str "company = " company)))))

(defn- get-companies []
  (-> (http/get "http://localhost:3000/companies"
                                {:as :json})
                      (get-in [:body :companies])))

(defn execute []
  (let [companies (get-companies)]
    (map (fn [company] {:status (:status (put-company company)) :company company}) companies)))
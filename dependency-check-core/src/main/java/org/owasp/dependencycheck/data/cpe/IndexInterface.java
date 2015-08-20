/*
 * Copyright 2015 OWASP.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.owasp.dependencycheck.data.cpe;

import java.io.IOException;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.owasp.dependencycheck.data.nvdcve.CveDB;

/**
 *
 * @author jeremy
 */
public interface IndexInterface {

    /**
     * Closes the CPE Index.
     */
    void close();

    /**
     * Retrieves a document from the Index.
     *
     * @param documentId the id of the document to retrieve
     * @return the Document
     * @throws IOException thrown if there is an IOException
     */
    Document getDocument(int documentId) throws IOException;

    /**
     * returns whether or not the index is open.
     *
     * @return whether or not the index is open
     */
    boolean isOpen();

    /**
     * Returns the number of CPE entries stored in the index.
     *
     * @return the number of CPE entries stored in the index
     */
    int numDocs();

    /**
     * Creates and loads data into an in memory index.
     *
     * @param cve the data source to retrieve the cpe data
     * @throws IndexException thrown if there is an error creating the index
     */
    void open(CveDB cve) throws IndexException;

    /**
     * Searches the index using the given search string.
     *
     * @param searchString the query text
     * @param maxQueryResults the maximum number of documents to return
     * @return the TopDocs found by the search
     * @throws ParseException thrown when the searchString is invalid
     * @throws IOException is thrown if there is an issue with the underlying Index
     */
    TopDocs search(String searchString, int maxQueryResults) throws ParseException, IOException;

    /**
     * Searches the index using the given query.
     *
     * @param query the query used to search the index
     * @param maxQueryResults the max number of results to return
     * @return the TopDocs found be the query
     * @throws CorruptIndexException thrown if the Index is corrupt
     * @throws IOException thrown if there is an IOException
     */
    TopDocs search(Query query, int maxQueryResults) throws CorruptIndexException, IOException;

}

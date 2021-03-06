/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.rave.portal.service;

import org.apache.rave.portal.model.Authority;
import org.apache.rave.portal.model.util.SearchResult;

public interface AuthorityService {

    /**
     * @param entityId unique identifier of the {@link org.apache.rave.portal.model.Authority}
     * @return Authority if it can be found, otherwise {@literal null}
     */
    Authority getAuthorityById(long entityId);

    /**
     * @param authorityName name of the authority, can be a role (ROLE_USER)
     * @return Authority if it can be found, otherwise {@literal null}
     */
    Authority getAuthorityByName(String authorityName);

    /**
     * @return a {@link SearchResult} with all {@link org.apache.rave.portal.model.Authority}'s
     */
    SearchResult<Authority> getAllAuthorities();

     /**
     * @return a {@link SearchResult} with the list of all default
     * {@link org.apache.rave.portal.model.Authority}'s
     */
    SearchResult<Authority> getDefaultAuthorities();
}

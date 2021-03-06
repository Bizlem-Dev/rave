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
package org.apache.rave.portal.model;

import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@XmlTransient
public interface Page {
    Long getId();
    void setId(Long id);

    String getName();
    void setName(String name);

    User getOwner();
    void setOwner(User owner);

    PageLayout getPageLayout();
    void setPageLayout(PageLayout pageLayout);

    List<Region> getRegions();
    void setRegions(List<Region> regions);

    PageType getPageType();
    void setPageType(PageType pageType);

    Page getParentPage();
    void setParentPage(Page parentPage);

    List<Page> getSubPages();
    void setSubPages(List<Page> subPages);

    List<PageUser> getMembers();
    void setMembers(List<PageUser> members);
}

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
package org.apache.rave.portal.model.conversion;

import org.apache.rave.model.ModelConverter;
import org.apache.rave.portal.model.JpaWidgetRating;
import org.apache.rave.portal.model.WidgetRating;
import org.springframework.stereotype.Component;

/**
 * Converts a WidgetRating to a JpaWidgetRating
 */
@Component
public class JpaWidgetRatingConverter implements ModelConverter<WidgetRating, JpaWidgetRating> {

    @Override
    public Class<WidgetRating> getSourceType() {
        return WidgetRating.class;
    }

    @Override
    public JpaWidgetRating convert(WidgetRating source) {
        return source instanceof JpaWidgetRating ? (JpaWidgetRating) source : createEntity(source);
    }

    private JpaWidgetRating createEntity(WidgetRating source) {
        JpaWidgetRating converted = null;
        if(source != null) {
            converted = new JpaWidgetRating();
            updateProperties(source, converted);
        }
        return converted;
    }

    private void updateProperties(WidgetRating source, JpaWidgetRating converted) {
        converted.setId(source.getId());
        converted.setScore(source.getScore());
        converted.setUserId(source.getUserId());
        converted.setWidgetId(source.getWidgetId());
    }
}

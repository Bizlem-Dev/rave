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

package org.apache.rave.inject;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.apache.rave.opensocial.service.impl.DefaultPersonService;
import org.apache.shindig.social.opensocial.spi.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:rave-shindig-test-applicationContext.xml",
        "classpath:rave-shindig-test-dataContext.xml"})
public class SpringBindingModuleTest {
    private Injector injector;

    @Autowired
    private SpringBindingModule module;

    @Before
    public void setup(){
        injector = Guice.createInjector(module);
    }

    @Test
    public void bindsScopedBean() {
        PersonService personService = injector.getInstance(PersonService.class);
        assertThat(personService, is(notNullValue()));
        assertThat(personService, is(instanceOf(DefaultPersonService.class)));
    }

    @Test
    public void bindsScopedBeanSingleton() {
        PersonService personService1 = injector.getInstance(PersonService.class);
        PersonService personService2 = injector.getInstance(PersonService.class);
        assertThat(personService1, is(sameInstance(personService2)));
    }



}

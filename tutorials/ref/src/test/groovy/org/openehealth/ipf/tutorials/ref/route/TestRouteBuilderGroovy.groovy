/*
 * Copyright 2008 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *     
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openehealth.ipf.tutorials.ref.route

import org.apache.camel.ValidationException
import org.apache.camel.spring.SpringRouteBuilder

/**
 * @author Martin Krasser
 */
class TestRouteBuilderGroovy extends SpringRouteBuilder {
    
    void configure() {

        // ------------------------------------------------------------
        //  Global error handling strategy
        // ------------------------------------------------------------
        
        onException(Exception.class).transform().exceptionMessage().to('mock:error-sys')

        // ------------------------------------------------------------
        //  Receive and validate order
        // ------------------------------------------------------------
        
        from('direct:order')
            .convertBodyTo(String.class)
            .validation('direct:validation')
            .to('seda:validated')
        
        from('direct:validation')
            .onException(ValidationException.class)
                .transform().exceptionMessage().to('mock:error-app').end()
            .to('validator:order/order.xsd')
            .transmogrify {'message valid'}
            
        // ------------------------------------------------------------
        //  Process order
        // ------------------------------------------------------------
        
        from('seda:validated')
            .unmarshal().gnode(false)
            .choice()
                .when { it.in.body.category.text() == 'animals' }
                    .to('direct:transform-animal-orders')
                .when { it.in.body.category.text() == 'books' }
                    .to('direct:transform-book-orders')
                .otherwise()
                    .to('direct:error-sys')
                    
         from('direct:transform-animal-orders')
             .transmogrify('animalOrderTransformer')
             .to('mock:animals')
        
         from('direct:transform-book-orders')
             .transmogrify('bookOrderTransformer')
             .params().builder()
             .to('mock:books')

    }
    
}

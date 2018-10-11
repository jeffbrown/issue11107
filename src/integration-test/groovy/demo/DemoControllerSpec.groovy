package demo

import grails.plugins.rest.client.RestBuilder
import grails.testing.mixin.integration.Integration
import spock.lang.Shared
import spock.lang.Specification

@Integration
class DemoControllerSpec extends Specification {

    @Shared
    def rest = new RestBuilder()

    void 'test creating a contact'() {
        when:
        def resp = rest.post("http://localhost:${serverPort}/contact") {
            json {
                nombre = 'Jeff'
                telefano = '12345678'
                ruta = {
                    descripcion = 'Some Description'
                }
            }
        }
        def contentType = resp.headers.getContentType()

        then:
        contentType.subtype == 'json'
        contentType.type == 'application'

        and:
        resp.json.descripcion == 'Some Description'
    }

    void 'test creating a contact with null ids'() {
        when:
        def resp = rest.post("http://localhost:${serverPort}/contact") {
            json {
                id = null
                nombre = 'Jeff'
                telefano = '12345678'
                ruta = {
//                    id = null
                    descripcion = 'Some Description'
                }
            }
        }
        def contentType = resp.headers.getContentType()

        then:
        contentType.subtype == 'json'
        contentType.type == 'application'

        and:
        resp.json.descripcion == 'Some Description'
    }
}

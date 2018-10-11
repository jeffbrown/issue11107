package demo

class DemoController {
	static responseFormats = ['json', 'xml']
	
    def createContact(Contact contact) {
        respond contact.ruta
    }
}

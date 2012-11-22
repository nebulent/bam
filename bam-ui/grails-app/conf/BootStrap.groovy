class BootStrap {

	def grailsApplication
	
    def init = { servletContext ->

        grailsApplication.domainClasses.each { clazz ->
            def oldConstructor = clazz.metaClass.retrieveConstructor(Map)
            clazz.metaClass.constructor = { Map data ->
                def instance = oldConstructor.newInstance(data)

                def idName = clazz.identifier.name
                if (data.containsKey(idName)) {
                    def unparsedValue = data."$idName"
                    def value
                    if (unparsedValue == null || unparsedValue == "")
                        value = null
                    else
                        value = clazz.identifier.type.valueOf(unparsedValue)

                    instance."$idName" = value
                }
                return instance
            }

        }
    }
    def destroy = {
    }
}

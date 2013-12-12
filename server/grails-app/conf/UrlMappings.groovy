class UrlMappings {

	static mappings = {
		
		"/api/authenticate(.${format})?"(controller:"user", action:"authenticate", method:"GET")
		"/api/unauthenticate(.${format})?"(controller:"user", action:"unauthenticate", method:"GET")
		"/api/books"(resources:"book")
        "/$controller/$action?/$id?(.${format})?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
	}
}

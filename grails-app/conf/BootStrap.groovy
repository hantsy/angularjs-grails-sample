import com.hantsylabs.grails.example.domain.Book

class BootStrap {

    def init = { servletContext ->
		new Book(title:"Java Persistence with Hibernate", author:"Gavin King", price:99.00).save()
		new Book(title:"Spring Live", author:"Matt Raible", price:29.00).save()	
    }
    def destroy = {
    }
}

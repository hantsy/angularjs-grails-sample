package com.hantsylabs.grails.example.security

import org.apache.commons.lang.builder.HashCodeBuilder

class PersonAuthority implements Serializable {

	private static final long serialVersionUID = 1

	Person person
	Authority authority

	boolean equals(other) {
		if (!(other instanceof PersonAuthority)) {
			return false
		}

		other.person?.id == person?.id &&
			other.authority?.id == authority?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (person) builder.append(person.id)
		if (authority) builder.append(authority.id)
		builder.toHashCode()
	}

	static PersonAuthority get(long personId, long authorityId) {
		PersonAuthority.where {
			person == Person.load(personId) &&
			authority == Authority.load(authorityId)
		}.get()
	}

	static PersonAuthority create(Person person, Authority authority, boolean flush = false) {
		new PersonAuthority(person: person, authority: authority).save(flush: flush, insert: true)
	}

	static boolean remove(Person u, Authority r, boolean flush = false) {

		int rowCount = PersonAuthority.where {
			person == Person.load(u.id) &&
			authority == Authority.load(r.id)
		}.deleteAll()

		rowCount > 0
	}

	static void removeAll(Person u) {
		PersonAuthority.where {
			person == Person.load(u.id)
		}.deleteAll()
	}

	static void removeAll(Authority r) {
		PersonAuthority.where {
			authority == Authority.load(r.id)
		}.deleteAll()
	}

	static mapping = {
		id composite: ['authority', 'person']
		version false
	}
}

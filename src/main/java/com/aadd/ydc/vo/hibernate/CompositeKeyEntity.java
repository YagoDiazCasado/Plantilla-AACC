package com.aadd.ydc.vo.hibernate;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class CompositeKeyEntity {

	// Si la embeded no funciona con objetos entro, hacer con longs y poner los
	// objetos fuera de la embedded como foraneas.
	// Que la composite solo posea los id de los objetos , y los objetos sean su
	// propia columna aparte fuera de ala embeddable

	@EmbeddedId
	private CompositeKey id;

	private String description;

	// Getters y Setters
	public CompositeKey getId() {
		return id;
	}

	public void setId(CompositeKey id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Embeddable
	public static class CompositeKey implements Serializable {
		private static final long serialVersionUID = 1L;
		private Long part1;
		private Long part2;

		public CompositeKey() {

		}

		public CompositeKey(Long part1, Long part2) {
			this.part1 = part1;
			this.part2 = part2;
		}

		// Getters, Setters, equals y hashCode
		public Long getPart1() {
			return part1;
		}

		public void setPart1(Long part1) {
			this.part1 = part1;
		}

		public Long getPart2() {
			return part2;
		}

		public void setPart2(Long part2) {
			this.part2 = part2;
		}

		// Obligatorio que lo tenga
		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			CompositeKey that = (CompositeKey) o;
			return Objects.equals(part1, that.part1) && Objects.equals(part2, that.part2);
		}

		@Override
		public int hashCode() {
			return Objects.hash(part1, part2);
		}
	}
}
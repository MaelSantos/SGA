package br.com.sga.entidade;

public abstract class Entidade {

	private Integer id;
	
	public Entidade() {
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Entidade) {
			Entidade entidade = (Entidade) obj;
			
			if(entidade.getId() == id)
				return true;	
		}
		
		return super.equals(obj);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}

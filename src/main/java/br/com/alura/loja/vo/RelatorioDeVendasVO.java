package br.com.alura.loja.vo;

import java.time.LocalDate;

public class RelatorioDeVendasVO {
	
	private String nomeProduto;
	private Long quantidadeVendida;
	private LocalDate dataUltimaCompra;
	
	public RelatorioDeVendasVO(String nomeProduto, Long quantidadeVendida, LocalDate dataUltimaCompra) {
		
		this.nomeProduto = nomeProduto;
		this.quantidadeVendida = quantidadeVendida;
		this.dataUltimaCompra = dataUltimaCompra;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public Long getQuantidadeVendida() {
		return quantidadeVendida;
	}

	public LocalDate getDataUltimaCompra() {
		return dataUltimaCompra;
	}

	@Override
	public String toString() {
		return "RelatorioDeVendasVO [nomeProduto=" + nomeProduto + ", quantidadeVendida=" + quantidadeVendida
				+ ", dataUltimaCompra=" + dataUltimaCompra + "]";
	}
	
	
	
	
	

}

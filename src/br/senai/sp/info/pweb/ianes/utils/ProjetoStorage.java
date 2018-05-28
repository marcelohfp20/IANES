package br.senai.sp.info.pweb.ianes.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjetoStorage {
	/**
	 * Guarda inforações sobre onde está rodando o projeto
	 */
	@Autowired
	private ServletContext context;

	/**
	 * 
	 * @return -> Caminho da pasta do projeto
	 */
	public String pegarCaminhoDoProjeto() {
		return context.getRealPath("");
	}

	public void armazenarFotoDePerfil(String nomeDoArquivo, byte[] dadosDoArquivo) throws IOException {

		// Pega o caminho para a pasta /assets/fotos
		String caminhoFotos = pegarCaminhoDoProjeto() + "assets/images";
		String caminhoArquivo = caminhoFotos + "/" + nomeDoArquivo;

		// Criar a pasta de fotos caso ela não exista
		File pastaFoto = new File(caminhoFotos);
		if (!pastaFoto.exists()) {
			pastaFoto.mkdirs(); // Cria a pasta
		}

		// Cria o arquivo na pasta
		File arquivoFoto = new File(caminhoArquivo);
		if (arquivoFoto.exists()) {
			arquivoFoto.delete();
		}

		arquivoFoto.createNewFile();

		// Salva o conteúdo no arquivo
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(arquivoFoto));
		bos.write(dadosDoArquivo);
		bos.close();
	}

}

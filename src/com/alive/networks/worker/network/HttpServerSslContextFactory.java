package com.alive.networks.worker.network;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;

import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alive.networks.ApiServerContext;
import com.alive.networks.config.ProcessConfig;

public final class HttpServerSslContextFactory {
	private static Logger logger = LoggerFactory.getLogger(HttpServerSslContextFactory.class);
	private static final String PROTOCOL = "TLS";
	private static final SSLContext SERVER_CONTEXT;
	private static final String SSL_KEY_ALIAS = "IPPUSH_KEY";
	private static final String SSL_CERT_ENGINE_NAME = "IPPUSH_CERT";
	private static final String KEY_STORE_TYPE = "JKS";

	private static final String SSL_KEY_FILE_PATH = ApiServerContext.HOME_PATH + "/" + ProcessConfig.SSL_KEY_FILE_PATH;
	private static final String SSL_CERT_FILE_PATH = ApiServerContext.HOME_PATH + "/" + ProcessConfig.SSL_CERT_FILE_PATH;

	static {
		String algorithm = Security.getProperty("ssl.KeyManagerFactory.algorithm");
		if (algorithm == null) {
			algorithm = "SunX509";
		}
		Security.addProvider(new BouncyCastleProvider());
		KeyPair keyPair = null;
		Certificate certificate = null;
		try {
			keyPair = createKeyPair(SSL_KEY_FILE_PATH);
			certificate = createCertificate(SSL_CERT_FILE_PATH);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		// SSLContext serverContext = null;
		try {
			KeyStore ks = KeyStore.getInstance(KEY_STORE_TYPE);
			ks.load(null);
			ks.setCertificateEntry(SSL_CERT_ENGINE_NAME, certificate);
			ks.setKeyEntry(SSL_KEY_ALIAS, keyPair.getPrivate(), " ".toCharArray(), new Certificate[] { certificate });
			
			KeyManagerFactory kmf = KeyManagerFactory.getInstance(algorithm);
			kmf.init(ks, " ".toCharArray());
			
			SERVER_CONTEXT = SSLContext.getInstance(PROTOCOL);
			SERVER_CONTEXT.init(kmf.getKeyManagers(), null, null);
		} catch (Exception e) {
			logger.error("" , e);
			throw new Error("Failed to initialize the server-side SSLContext", e);
		}
	}

	public static SSLContext getServerContext() {
		return SERVER_CONTEXT;
	}

	private static KeyPair createKeyPair(String keyFilePath) throws Exception {
		File keyFile = null;
		KeyPair keyPair = null;
		FileReader reader = null;
		try {
			keyFile = new File(keyFilePath); // private key file in PEM format
			logger.info("key file : " + keyFile.exists());
			reader = new FileReader(keyFile);
			PEMParser pemParser = new PEMParser(reader);
			JcaPEMKeyConverter converter = new JcaPEMKeyConverter().setProvider(BouncyCastleProvider.PROVIDER_NAME);
			Object object = pemParser.readObject();
			keyPair = converter.getKeyPair((PEMKeyPair) object);
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		return keyPair;
	}

	private static Certificate createCertificate(String certFilePath) throws IOException, CertificateException {
		File certFile = null;
		Certificate certificate = null;
		FileReader reader = null;
		try {
			certFile = new File(certFilePath); // certificate file in PEM format
			logger.info("cert File : " + certFile.exists());
			reader = new FileReader(certFile);
			PEMParser publicPemParser = new PEMParser(reader);
			Object obj = publicPemParser.readObject();
			JcaX509CertificateConverter converter = new JcaX509CertificateConverter();
			converter.setProvider(BouncyCastleProvider.PROVIDER_NAME);
			certificate = converter.getCertificate((X509CertificateHolder) obj);
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		return certificate;
	}
}
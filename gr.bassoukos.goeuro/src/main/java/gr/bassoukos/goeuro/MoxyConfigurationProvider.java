package gr.bassoukos.goeuro;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.moxy.json.MoxyJsonConfig;

/**
 * A provider that configures MOXy. The default configuration is acceptable for
 * JSON, except that we set the attribute prefix to the empty string.
 * <p>
 * We could also have set it to the underscore character ("_").
 * 
 * @author abas
 */
@Provider
public class MoxyConfigurationProvider implements
		ContextResolver<MoxyJsonConfig> {
	private final MoxyJsonConfig config;

	public MoxyConfigurationProvider() {
		config = new MoxyJsonConfig().setAttributePrefix("").setIncludeRoot(
				false);
	}

	public MoxyJsonConfig getContext(Class<?> objectType) {
		return config;
	}
}
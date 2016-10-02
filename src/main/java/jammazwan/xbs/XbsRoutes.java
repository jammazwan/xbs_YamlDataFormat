package jammazwan.xbs;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.snakeyaml.SnakeYAMLDataFormat;
import org.apache.camel.model.dataformat.YAMLLibrary;
import org.apache.camel.spi.DataFormat;

public class XbsRoutes extends RouteBuilder {

	DataFormat snack = new SnakeYAMLDataFormat();

	@Override
	public void configure() throws Exception {
		from("direct:xbs").marshal().yaml(YAMLLibrary.SnakeYAML).to("file://.?fileName=my.yml");

		from("file://.?noop=true&fileName=your.yml").unmarshal("snake").process(new Processor() {
			public void process(Exchange exchange) throws Exception {
				System.err.println("\n\n\n>>>>>>> " + exchange.getIn().getBody().getClass());
			}
		});
	}
}

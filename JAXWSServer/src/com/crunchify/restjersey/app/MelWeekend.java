package com.crunchify.restjersey.app;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.crunchify.restjersey.bo.TweetBo;
import com.crunchify.restjersey.util.Const;

@Path("/getMelWeekend")

public class MelWeekend {
	@GET
	@Produces("application/json")
	public Response getMelWeekend() throws Exception {
		TweetBo bo = new TweetBo(Const.Tweet);
		return new TweetService().getData(bo.getMelWeekend());
	}
}

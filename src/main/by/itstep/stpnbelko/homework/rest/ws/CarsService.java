package by.itstep.stpnbelko.homework.rest.ws;

import by.itstep.stpnbelko.homework.dao.impl.CarsDAO;
import by.itstep.stpnbelko.homework.model.Car;

import java.util.Date;
import java.util.List;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/cars")
public class CarsService {

    private CarsDAO dao = CarsDAO.getInstance();


    @GET
    @Path("/is-alive")
    @Produces(MediaType.TEXT_PLAIN)
    public String isAlive() {
        return "Server Time : " + new Date();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response showAll() {
        List<Car> all = dao.all();
        if (all.size() > 0) {
            return Response.ok(all).build();
        }
        return Response.noContent().build();
    }


    @GET
    @Path("/all-xml")
    @Produces(MediaType.APPLICATION_XML)
    public Response showAllInXml(){
        List<Car> all = dao.all();
        if(all.size() > 0){
            GenericEntity<List<Car>> genericEntity = new GenericEntity<List<Car>>(all) { };

            return Response.ok(genericEntity).build();
        }


        return Response.noContent().build();
    }

    @GET
    @Path("/first")
    @Produces(MediaType.APPLICATION_JSON)
    public Response first() {
        List<Car> all = dao.all();

        if (all.isEmpty()) {
            return Response.noContent().build();
        }

        return Response.ok(all.get(0)).build();
    }


    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id) {
        Car car = dao.getById(id);

        if (car != null) {
            return Response.ok(car).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCar(Car car) {
        int id = dao.add(car);
        return Response.ok(car).build();
    }


    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Car car) {
        boolean isUpdated = dao.update(car);
        if (isUpdated) {
            return Response.ok("UPDATED").build();
        } else {
            return Response.status(Response.Status.NOT_MODIFIED).build();
        }
    }

}

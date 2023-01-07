package simple.dtu.pay;

import simple.dtu.pay.PaymentService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/payments")
public class PaymentResource {

    private PaymentService paymentService = new PaymentService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response savePayment(simple.dtu.pay.Payment payment) {
        try {
            return Response.ok(paymentService.savePayment(payment)).build();
        } catch (Exception e) {
            return Response.status(404).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPayments() {
        return Response.ok(paymentService.getPayments()).build();
    }

    @DELETE
    public Response deletePayments() {
        paymentService.deletePayments();
        return Response.ok().build();
    }

}
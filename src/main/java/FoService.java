import model.FoaasResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FoService {
    @GET("asshole/{from}")
    Call<FoaasResponse> asshole(@Path("from") String from);
    @GET("awesome/{from}")
    Call<FoaasResponse> awesome(@Path("from") String from);
    @GET("because/{from}")
    Call<FoaasResponse> because(@Path("from") String from);
    @GET("bucket/{from}")
    Call<FoaasResponse> bucket(@Path("from") String from);
    @GET("bday/{from}")
    Call<FoaasResponse> bday(@Path("from") String from);
    @GET("bag/{from}")
    Call<FoaasResponse> baf(@Path("from") String from);
    @GET("everyone/{from}")
    Call<FoaasResponse> everyone(@Path("from") String from);
    @GET("flying/{from}")
    Call<FoaasResponse> flying(@Path("from") String from);
    @GET("everything/{from}")
    Call<FoaasResponse> everything(@Path("from") String from);
    @GET("family/{from}")
    Call<FoaasResponse> family(@Path("from") String from);
    @GET("give/{from}")
    Call<FoaasResponse> give(@Path("from") String from);
    @GET("fyyff/{from}")
    Call<FoaasResponse> fyyff(@Path("from") String from);
    @GET("horse/{from}")
    Call<FoaasResponse> horse(@Path("from") String from);
    @GET("life/{from}")
    Call<FoaasResponse> life(@Path("from") String from);
    @GET("looking/{from}")
    Call<FoaasResponse> looking(@Path("from") String from);
    @GET("maybe/{from}")
    Call<FoaasResponse> maybe(@Path("from") String from);
    @GET("me/{from}")
    Call<FoaasResponse> me(@Path("from") String from);
    @GET("morning/{from}")
    Call<FoaasResponse> mornin(@Path("from") String from);
    @GET("no/{from}")
    Call<FoaasResponse> no(@Path("from") String from);
    @GET("retard/{from}")
    Call<FoaasResponse> retard(@Path("from") String from);
}

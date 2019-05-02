/*
 * Copyright 2019 igur.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import model.FoaasResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Application {

    public static void main(String [] args ) throws InvocationTargetException, IllegalAccessException {
        ExecutorService httpExecutor = Executors.newFixedThreadPool(8,
                new WorkerThreadFactoryBuilder().
                        daemon(true).
                        name("retrofit_callback_pumping_thread").
                        priorty(Thread.NORM_PRIORITY).
                        build());
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(chain -> {
            Request request = chain.request().newBuilder().addHeader("Accept", "application/json").build();
            return chain.proceed(request);
        });


        OkHttpClient okHttpClient = httpClient.build();

        if (httpExecutor == okHttpClient.dispatcher().executorService()) {
            System.out.println("they are equal");
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://foaas.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .callbackExecutor(httpExecutor)
                .client(okHttpClient)
                .build();

        FoService service = retrofit.create(FoService.class);

        Map<String, String> env = System.getenv();

        Class clazz = FoService.class;
        Method[] methods = clazz.getDeclaredMethods();
        assert methods.length > 0;

        @SuppressWarnings("unchecked")
        Call<FoaasResponse> yoda  = (Call<FoaasResponse>) methods[new Random().nextInt(methods.length)].invoke(service, env.getOrDefault("USER", "me"));

        yoda.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<FoaasResponse> call, Response<FoaasResponse> response) {
                System.out.println(response.body());
                okHttpClient.dispatcher().executorService().shutdown();
            }

            @Override
            public void onFailure(Call<FoaasResponse> call, Throwable throwable) {
                System.out.println(throwable.toString());
                okHttpClient.dispatcher().executorService().shutdown();
            }
        });
    }
}

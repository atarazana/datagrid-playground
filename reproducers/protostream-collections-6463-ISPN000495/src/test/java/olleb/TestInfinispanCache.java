package olleb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.infinispan.Cache;
import org.infinispan.commons.api.CacheContainerAdmin;
import org.infinispan.commons.marshall.ProtoStreamMarshaller;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.olleb.test.model.B;
import com.olleb.test.model.CB;
import com.olleb.test.model.SchInitializerImpl;

class TestInfinispanCache {

        private static Cache<String, Object> twoListCache;

        private B b1 = new B();
        private B b2 = new B();

        List<B> listOne;
        List<B> listTwo;

        public static Configuration cfg(String mt) {
                ConfigurationBuilder config = new ConfigurationBuilder();
                config.encoding().mediaType(mt);
                return config.build();
        }

        @BeforeAll
        static void setUp() {
                GlobalConfigurationBuilder global = GlobalConfigurationBuilder.defaultClusteredBuilder();
                global.serialization().addContextInitializers(new SchInitializerImpl())
                                .marshaller(new ProtoStreamMarshaller());
                DefaultCacheManager cacheManager = new DefaultCacheManager(global.build());

                twoListCache = cacheManager.administration().withFlags(CacheContainerAdmin.AdminFlag.VOLATILE)
                                .createCache("TWO_BIN", cfg("application/x-protostream"));

        }

        @BeforeEach
        void beforeEach() {
                B b1 = new B();
                B b2 = new B();
                b1.setA("123456");
                b2.setA("654321");
                listOne = new ArrayList<>();
                listTwo = new ArrayList<>();
                listOne.add(b1);
                listTwo.add(b1);
                listTwo.add(b2);

        }

        @Test
        void testTwoItemListCacheBad() {
                ArrayList<B> list = new ArrayList<>();
                list.add(b1);
                list.add(b2);
                twoListCache.put("one", list);
                assertEquals(twoListCache.get("one"), list, "The list is not the same");
        }

        @Test
        void testTwoItemListCache() {
                CB b = new CB(listOne);
                CB b2 = new CB(listTwo);
                twoListCache.put("one", b);
                twoListCache.put("two", b2);
                assertEquals(((CB) twoListCache.get("two")).getBs(), listTwo, "The list is not the same");
        }

}

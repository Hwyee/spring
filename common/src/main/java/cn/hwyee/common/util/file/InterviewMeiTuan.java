package cn.hwyee.common.util.file;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import generator.domain.KeyctmArchiveContent;
import generator.service.KeyctmArchiveContentService;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author hwyee@foxmail.com
 * @version 1.0
 * @ClassName InterviewMeiTuan
 * @description
 * @date 2023/8/23
 * @since JDK 1.8
 */
public class InterviewMeiTuan {


    /**
     * :
     * 1、题目：编写一个Java函数，实现批量获取数据的功能（BService.get(List<Integer> ids)）。具体要求如下：
     * 1)提供一个函数BService.get(List<Integer> ids)，支持最多传入100个id；
     * 2)在BService.get((List<Integer> ids)函数内部，需要将传入的id列表分批（每批10个id）进行调用AService.get(List<Integer> ids)函数获取数据；
     * 3)BService.get((List<Integer> ids)函数需要返回所有批次获取的数据的合并结果，即一个包含所有数据的List<Integer>；
     * <p>
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2023/8/23
     */
    static class Solution1 {
        static class BService {

            /**
             * 最大ID数量
             */
            static final int MAX_IDS = 100;

            List<Integer> get(List<Integer> ids) {
                if (ids != null && ids.size() <= MAX_IDS) {
                    AService aService = new AService();
                    int groupIds = 10;
                    List<Integer> list = new ArrayList<>();
                    for (int i = 0; i < ids.size(); i += groupIds) {
                        List<Integer> result = aService.get(ids.subList(i, Math.min(ids.size(), i + groupIds)));
                        list.addAll(result);
                    }
                    return list;
                } else {
                    throw new IllegalArgumentException("id不能超过100");
                }
            }

            public List<Hotel> batchGetHotelByPoiId(List<Long> poiIds) {
                return null;
            }
            public  Integer get() {
                return 1;
            }
        }

        static class AService {
            List<Integer> get(List<Integer> ids) {
                return ids;
            }

            public List<Long> getHotelByParnterId(List<Long> partnerIds) {

                return partnerIds;
            }

            public  Integer get() {
                return 1;
            }
        }
    }

    static class CService {
        public List<Contact> batchGetContactBypoiIds(List<Long> poiIds) {
            return null;
        }
        public  Integer get() {
            return 1;
        }
    }

    /**
     * :
     * 2、根据所提供的方法，完成题目：批量根据供应商ID（partnerID）获取供应商下所关联的酒店名称（poiName）以及酒店的联系人姓名（contactName）;
     * * 方法1）AService.getHotelByParnterId(List<Long> partnerId),根据供应商ID，返回关联的poiId集合;
     * * 方法2）BService.batchGetHotelByPoiId(List<Long> poiIds),批量根据酒店id集合，返回酒店信息集合，酒店信息包含酒店ID，酒店名称；
     * * 方法3）CService.batchGetContactBypoiIds(List<Long> poiIds),批量根据酒店id集合，返回酒店下联系人信息集合，酒店联系人信息包含酒店ID(poiID),联系人名称（contactName）;
     * * 小贴士：同一个酒店联系人若有多个，任意取一个；默认一次查询可返回数据，无需考虑分批查询；方法中可能因为某些数据问题，导致数据缺失，结合日常工作场景，完整考虑代码健壮性；
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2023/8/23 23:00
     */
    static class Solution2 {

        private Solution1.AService aService;
        private Solution1.BService bService;
        private CService cService;

        public List<PoiContactName> getHotelContactsByPartnerId(List<Long> partnerIds) {
            if (partnerIds == null || partnerIds.isEmpty()) {
                return Collections.emptyList();
            }
            List<PoiContactName> poiContactNames = new ArrayList<>();

            // 根据供应商ID，返回关联的poiId集合
            List<Long> poiIds = aService.getHotelByParnterId(partnerIds);
            if (poiIds == null || poiIds.isEmpty()) {
                return Collections.emptyList();
            }
            // 批量根据酒店id集合，返回酒店信息集合，酒店信息包含酒店ID，酒店名称
            List<Hotel> hotels = bService.batchGetHotelByPoiId(poiIds);
            if (hotels == null || hotels.isEmpty()) {
                return Collections.emptyList();
            }

            // 量根据酒店id集合，返回酒店下联系人信息集合，酒店联系人信息包含酒店ID(poiID),联系人名称（contactName）
            List<Contact> contacts = cService.batchGetContactBypoiIds(poiIds);

            // 返回酒店名称和联系人名称
            for (Hotel hotel : hotels) {
                PoiContactName poiContactName = new PoiContactName();
                poiContactName.setPoiName(hotel.poiName);
                poiContactName.setContactName(contacts == null ? "" : contacts.stream().
                        filter(t -> t.poiId.equals(hotel.poiId)).findFirst().orElse(new Contact()).contactName);
                poiContactNames.add(poiContactName);
            }
            return poiContactNames;
        }
    }

    /**
     * 酒店信息和联系人信息:
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2023/8/23
     */
    static class PoiContactName {
        /**
         * 酒店名称
         */
        private String poiName;
        /**
         * 联系人姓名
         */
        private String contactName;

        public String getPoiName() {
            return poiName;
        }

        public void setPoiName(String poiName) {
            this.poiName = poiName;
        }

        public String getContactName() {
            return contactName;
        }

        public void setContactName(String contactName) {
            this.contactName = contactName;
        }
    }

    /**
     * 酒店信息:
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2023/8/23
     */
    static class Hotel {
        /**
         * 酒店ID
         */
        private String poiId;
        /**
         * 酒店名称
         */
        private String poiName;
    }

    /**
     * 联系人信息:
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2023/8/23
     */
    static class Contact {
        /**
         * 酒店ID
         */
        private String poiId;
        /**
         * 联系人姓名
         */
        private String contactName;
    }

    /**
     * 编写一个Java函数，通过调用AService.get()、BService.get()、CService.get()三个接口，获取三个整数，然后将这三个整数累加，最终返回累加的值。要求：
     * 小贴士：调用三个接口的操作需要并行执行，以提高效率；累加操作需要在获取三个整数的操作完成后进行，因此需要保证三个整数均已获取后才能进行累加操作；考虑多线程安全问题。:
     *
     * @author hui
     * @version 1.0
     * @return
     * @date 2023/8/23
     */
    static class Solution3 {
        private Solution1.AService aService;
        private Solution1.BService bService;
        private CService cService;

        Integer sum(){
            CompletableFuture<Integer> taskA = CompletableFuture.supplyAsync(() -> aService.get());
            CompletableFuture<Integer> taskB = CompletableFuture.supplyAsync(() -> bService.get());
            CompletableFuture<Integer> taskC = CompletableFuture.supplyAsync(() -> cService.get());
            AtomicReference<Integer> sum = new AtomicReference<>(0);
            CompletableFuture.allOf(taskA,taskB,taskC).thenRun( () -> {
                try {
                    sum.set(taskA.get() + taskB.get() + taskC.get());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
            });
            return sum.get();
        }
    }
    
    /**
     * 根据给定的表，设计出一个定时任务的一部分，根据要求遍历表，根据 biz_type ,status ,create_time 进行查询,要求查询创建时间在当天，且status = 0 ，并且content中A的值等于"招聘"的行，并调用给出的方法ArchiveService.complete(Long id)方法将status置为1；
     * 小贴士：注意代码健壮性
     * CREATE TABLE `keyctm_archive_content` (
     *   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
     *   `biz_type` int(10) NOT NULL DEFAULT '0' COMMENT '业务',
     *   `archive_type` varchar(256) DEFAULT NULL COMMENT '档案类型',
     *   `content` json DEFAULT NULL COMMENT '档案内容',
     *   `status` int(10) DEFAULT '1' COMMENT '状态，1正式0草稿',
     *   `creator` int(10) NOT NULL DEFAULT '0' COMMENT '创建人',
     *   `modifier` int(10) NOT NULL DEFAULT '0' COMMENT '修改人',
     *   `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     *   `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
     *   PRIMARY KEY (`id`)
     * ) ENGINE=InnoDB AUTO_INCREMENT=233826 DEFAULT CHARSET=utf8mb4 COMMENT='大客档案表'
     *
     * content中数据结构为：
     * {
     * 	"A": "A属性",
     * 	"B": "B属性",
     * 	"C": "C属性"
     * }: 
     * 
     * @author hui
     * @version 1.0
     * @return
     * @date 2023/8/23
     */
    static class Solution4{
        KeyctmArchiveContentService keyctmArchiveContentService;
        void timingTask(Integer bizType,Integer status){
            List<KeyctmArchiveContent> list = keyctmArchiveContentService.list(Wrappers.lambdaQuery(KeyctmArchiveContent.class).
                    eq(KeyctmArchiveContent::getBizType, bizType).
                    eq(KeyctmArchiveContent::getStatus, Status.DRAFT.getCode()).
                    apply("date(create_time) = curdate() and json_extract(content, '$.A') = '招聘'"));
            for (KeyctmArchiveContent keyctmArchiveContent : list) {
                ArchiveService.complete(keyctmArchiveContent.getId());
            }
        }
    }
    static class ArchiveService{
        static KeyctmArchiveContentService keyctmArchiveContentService;

        static void complete(Long id){
            KeyctmArchiveContent keyctmArchiveContent = new KeyctmArchiveContent();
            keyctmArchiveContent.setId(id);
            keyctmArchiveContent.setStatus(Status.FORMAL.getCode());
            keyctmArchiveContentService.updateById(keyctmArchiveContent);
       }
    }

    enum Status{
        FORMAL(1,"正式"),
        DRAFT(0,"草稿");
        final Integer code;
        final String name;

        public Integer getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        Status(Integer code, String name) {
            this.code = code;
            this.name = name;
        }
    }
}

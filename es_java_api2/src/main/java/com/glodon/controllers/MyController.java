package com.glodon.controllers;

import com.glodon.model.Book;
import com.glodon.model.Position;
import com.glodon.util.DataTransfer;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author liuwg-a
 * @date 2018/9/16 15:38
 * @description
 */
@RestController
@RequestMapping("/info")
public class MyController {
    @Autowired
    private TransportClient client;

    @RequestMapping("/go")
    public ResponseEntity<String> get() {
        return new ResponseEntity<>("go2", HttpStatus.OK);
    }

    //向es中新增数据
    @RequestMapping(value = "/position")
    public String insert() {
        List<Position> list= DataTransfer.readFile();
        try {
            // 利用内置的json文档生成工具自动序列化放进去的属性
            for(int i=0;i<list.size();i++) {
                Position position=list.get(i);
                XContentBuilder builder = XContentFactory.jsonBuilder().startObject()
                        .field("id", position.getId())
                        .field("job_title", position.getJob_title())
                        .field("job_salary", position.getJob_salary())
                        .field("work_place", position.getWork_place())
                        .field("experience", position.getExperience())
                        .field("education", position.getEducation())
                        .field("company", position.getCompany())
                        .field("company_type", position.getCompany_type())
                        .field("company_state", position.getCompany_state())
                        .field("company_staff", position.getCompany_staff())
                        .field("publis_name", position.getPublis_name())
                        .field("publis_job", position.getPublis_job())
                        .endObject();
                // 查看序列化出来的JSON文档
                System.out.println(Strings.toString(builder));
                // 第一个参数是索引Index，第二个参数是类型Type(这个概念会在将来删除)，第三个参数是id，（都可缺省,ES自动生成）
                IndexResponse response = client.prepareIndex("info", "position", position.getId())
                        .setSource(builder).get();
            }
            return "初始化成功";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 根据id查询文件
     * @param id book's id
     * @return document
     */
    @RequestMapping(value = "/position/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> get(@PathVariable(value = "id") String id) {
        GetResponse response = client.prepareGet("info", "position", id).get();
        return new ResponseEntity<>(response.getSource().toString(), HttpStatus.OK);
    }

    /**
     * 根据id删除book
     */
    @RequestMapping(value = "book/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable("id") String id) {
        System.out.println(id);
        DeleteResponse response = client.prepareDelete("book", "jack's book", id).get();
        return new ResponseEntity<>(response.getResult().toString(), HttpStatus.OK);
    }

    /**
     * 更新book
     */
    @RequestMapping(value = "/book/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> update(@PathVariable("id") String id, @RequestBody Book book) throws IOException, ExecutionException, InterruptedException {
        // 指定更新文档的所在索引、类型和id
        UpdateRequest updateRequest = new UpdateRequest("book", "jack's book", id);
        // 创建更新的文档
        XContentBuilder xContentBuilder = XContentFactory.jsonBuilder().startObject();
        if (StringUtils.isNotBlank(book.getName())) {
            xContentBuilder.field("name", book.getName());
        }
        if (StringUtils.isNotBlank(book.getAuthor())) {
            xContentBuilder.field("author", book.getAuthor());
        }
        xContentBuilder.endObject();
        updateRequest.doc(xContentBuilder);
        // 执行更新操作并获取执行结果
        UpdateResponse response = client.update(updateRequest).get();
        return new ResponseEntity<>(response.getResult().toString(), HttpStatus.OK);
    }
}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.xgh.recruitcmbs.dao.write;

import com.xgh.recruitcmbs.entity.FileData;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface IFileDataDaoW {
    int add(FileData var1);

    int update(FileData var1);

    int deleteById(long var1);

    int deleteByNid(long var1);

    int updateByDataSource(Map<String, Object> var1);

    int addBatch(List<FileData> var1);
}
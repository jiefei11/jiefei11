package com.dzxx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dzxx.common.R;
import com.dzxx.entity.Category;

public interface CategoryService extends IService<Category> {
    R<Page> pages(int page, int pageSize);

    R<String> insert(Category category);

    void delete(Long id);
}

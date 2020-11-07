package com.tasks.services.parser;

import javax.transaction.Transactional;

public interface ParseCsvServices {
    @Transactional
    void readAllDataAtOnce();
}

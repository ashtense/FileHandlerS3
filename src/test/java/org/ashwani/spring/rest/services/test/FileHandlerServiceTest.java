package org.ashwani.spring.rest.services.test;

import org.ashwani.spring.rest.exceptions.UploaderNotFoundException;
import org.ashwani.spring.rest.file.handlers.S3FileHandler;
import org.ashwani.spring.rest.services.FileHandler;
import org.ashwani.spring.rest.services.FileHandlerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collection;

public class FileHandlerServiceTest {

    @InjectMocks
    private FileHandlerService fileHandlerService;

    @Mock
    Collection<FileHandler> fileHandlers;

    @Mock
    private S3FileHandler s3FileHandler;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = UploaderNotFoundException.class)
    public void getAllFilesWhenHandlerNotFoundTest(){
        this.fileHandlerService.getAllFiles();
    }

}

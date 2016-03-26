package com.aeg.ims.transfer.config;


import java.io.File;

import com.aeg.ims.transfer.batch.tasklet.FtpGetRemoteFilesTasklet;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;


@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    @Qualifier("jobRepository")
    private JobRepository jobRepository;

    @Bean
    public SessionFactory myFtpSessionFactory()
    {
        DefaultSftpSessionFactory sftpSessionFactory = new DefaultSftpSessionFactory();
        sftpSessionFactory.setHost("sftp.ameresco.com");
        sftpSessionFactory.setAllowUnknownKeys(true);
        sftpSessionFactory.setPort(22);
        sftpSessionFactory.setUser("sftp_a037_g30");
        sftpSessionFactory.setPassword("$mHs#4&U");

        return sftpSessionFactory;
    }

    @Bean
    @Scope(value="step")
    public FtpGetRemoteFilesTasklet myFtpGetRemoteFilesTasklet()
    {
        FtpGetRemoteFilesTasklet ftpTasklet = new FtpGetRemoteFilesTasklet();
        ftpTasklet.setRetryIfNotFound(true);
        ftpTasklet.setDownloadFileAttempts(3);
        ftpTasklet.setRetryIntervalMilliseconds(10000);
        ftpTasklet.setFileNamePattern("README");
        //ftpTasklet.setFileNamePattern("TestFile");
        ftpTasklet.setRemoteDirectory("/Test");
        ftpTasklet.setLocalDirectory(new File(System.getProperty("java.io.tmpdir")));
        ftpTasklet.setSessionFactory(myFtpSessionFactory());

        return ftpTasklet;
    }

    @Bean
    public SimpleJobLauncher jobLauncher() {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository);
        return jobLauncher;
    }

/*https://keyholesoftware.com/2015/06/29/spring-batch-javaconfig/*/
    /*@Bean
    public ItemReader<TickerData> reader() throws MalformedURLException {
        FlatFileItemReader<TickerData> reader = new FlatFileItemReader<TickerData>();
        reader.setResource(new UrlResource("http://finance.yahoo.com/d/quotes.csv?s=XOM+IBM+JNJ+MSFT&amp;f=snd1ol1p2"));
        reader.setLineMapper(new DefaultLineMapper<TickerData>() {{
            setLineTokenizer(new DelimitedLineTokenizer());
            setFieldSetMapper(new TickerFieldSetMapper());
        }});
        return reader;
    }
    @Bean
    public ItemProcessor<TickerData, TickerData> processor() {
        return new TickerPriceProcessor();
    }

    @Bean
    public ItemWriter<TickerData> writer() {
        return new LogItemWriter();
    }
    @Bean
    public Job TickerPriceConversion() throws MalformedURLException {
        return jobs.get("TickerPriceConversion").start(convertPrice()).build();
    }

    @Bean
    public Step convertPrice() throws MalformedURLException {
        return steps.get("convertPrice")
                .<TickerData, TickerData> chunk(5)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }*/
}

package com.aeg.ims.transfer.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class FlatfileToDbJobConfiguration {

   /* @Autowired
    private JobBuilderFactory jobBuilders;

    @Autowired
    private StepBuilderFactory stepBuilders;

    @Autowired
    private InfrastructureConfiguration infrastructureConfiguration;

    @Bean
    public Job flatfileToDbJob(){
        return jobBuilders.get("flatfileToDbJob")
                .listener(protocolListener())
                .start(step())
                .build();
    }

    @Bean
    public Step step(){
        return stepBuilders.get("step")
                .<Partner,Partner>chunk(1)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .listener(logProcessListener())
                .build();
    }

    @Bean
    public FlatFileItemReader<Partner> reader(){
        FlatFileItemReader<Partner> itemReader = new FlatFileItemReader<Partner>();
        itemReader.setLineMapper(lineMapper());
        itemReader.setResource(new ClassPathResource("partner-import.csv"));
        return itemReader;
    }

    @Bean
    public LineMapper<Partner> lineMapper(){
        DefaultLineMapper<Partner> lineMapper = new DefaultLineMapper<Partner>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames(new String[]{"name","email"});
        lineTokenizer.setIncludedFields(new int[]{0,2});
        BeanWrapperFieldSetMapper<Partner> fieldSetMapper = new BeanWrapperFieldSetMapper<Partner>();
        fieldSetMapper.setTargetType(Partner.class);
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }

    @Bean
    public ItemProcessor<Partner,Partner> processor(){
        return new LogItemProcessor();
    }

    @Bean
    public ItemWriter<Partner> writer(){
        JdbcBatchItemWriter<Partner> itemWriter = new JdbcBatchItemWriter<Partner>();
        itemWriter.setSql("INSERT INTO PARTNER (NAME, EMAIL) VALUES (:name,:email)");
        itemWriter.setDataSource(infrastructureConfiguration.dataSource());
        itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Partner>());
        return itemWriter;
    }

    @Bean
    public ProtocolListener protocolListener(){
        return new ProtocolListener();
    }

    @Bean
    public LogProcessListener logProcessListener(){
        return new LogProcessListener();
    }*/
}
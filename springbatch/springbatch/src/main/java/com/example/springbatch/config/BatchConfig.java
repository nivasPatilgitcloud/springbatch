package com.example.springbatch.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.springbatch.model.Foodtb;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
    private JobRepository jobRepository;
	
	private final StepBuilderFactory stepBuilderFactory;

    public BatchConfig(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }
	
	@Bean
	public Job jobbean(JobCompletionNotificationImpl listener,
			Step steps) {
		return new JobBuilder("job")
				.listener(listener)
				.start(steps)
				.repository(jobRepository)
				.build();
	}
	@Bean
	public Step steps(@Qualifier("transactionManager") PlatformTransactionManager transactionManager,
            FlatFileItemReader<Foodtb> reader,
            ItemProcessor<Foodtb, Foodtb> processor,
            ItemWriter<Foodtb> itemWriter) {
//		these two lines not working
//		return new  StepBuilder("jobstep",jobrepository)
//				.<Foodtb,Foodtb>chunk(5,transactionmanager)
		
		return stepBuilderFactory.get("myStep")
                .<Foodtb, Foodtb>chunk(5)
                .reader(reader)
                .processor(processor)
                .writer(itemWriter)
                .transactionManager(transactionManager)
                .build();
	}
	
	@Bean
	//Reader
	//ID,Date,Region,City,Category,Product,Qty,UnitPrice,TotalPrice
	public FlatFileItemReader<Foodtb> reader(){
		return new FlatFileItemReaderBuilder<Foodtb>()
				.name("itemreader")
				.resource(new ClassPathResource("sampledatafoodsales_analysis.csv"))
				.delimited()
				.names("id","date","region","city","category","product","qty","unit_price","total_price")
				.targetType(Foodtb.class)
				.build();
	}
	//Processor
	@Bean
	public ItemProcessor<Foodtb, Foodtb> itemprocessor(){
		return new CustomItemProcessor();
	}
	
	//Writer
	@Bean
	public ItemWriter<Foodtb> itemwriter(DataSource datasource){
		return new JdbcBatchItemWriterBuilder<Foodtb>()
				.sql("INSERT INTO foodtb (id, date, region, city, category, product, qty, unit_price, total_price)\r\n" + 
						"VALUES (:id, :date, :region, :city, :category, :product, :qty, :unit_price, :total_price)\r\n" + 
						"")
				.dataSource(datasource)
				.beanMapped()
				.build();
	}
}

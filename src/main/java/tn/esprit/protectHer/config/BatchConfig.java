package tn.esprit.protectHer.config;

import java.util.Random;

import tn.esprit.protectHer.batch.InvitationMapper;
import tn.esprit.protectHer.batch.InvitationProcessor;
import tn.esprit.protectHer.batch.InvitationWriter;
import tn.esprit.protectHer.entity.Invitation;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@EnableBatchProcessing
@Configuration

public class BatchConfig {
	
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    DataSource dataSource;
    
    @Bean(name = "emailSenderJob")
    public Job emailSenderJob() {
        return this.jobBuilderFactory.get("emailSenderJob" + new Random().nextInt())
                .start(emailSenderStep())
                .build();
    }

    @Bean
    public Step emailSenderStep() {
        return this.stepBuilderFactory
                .get("emailSenderStep")
                .<Invitation, Invitation>chunk(100)
                .reader(invitationItemReader())
                .processor(invitationItemProcessor())
                .writer(orderWriter())
                .build();
    }

    @Bean
    public ItemProcessor<Invitation, Invitation> invitationItemProcessor() {
        return new InvitationProcessor();
    }

    @Bean
    public ItemWriter<Invitation> orderWriter() {
        return new InvitationWriter();
    }

    @Bean
    public ItemReader<Invitation> invitationItemReader() {
        String sql = "select * from invitation where status = false";
        return new JdbcCursorItemReaderBuilder<Invitation>()
                .name("invitationItemReader")
                .sql(sql)
                .dataSource(dataSource)
                .rowMapper(new InvitationMapper())
                .build();
    }
    
}
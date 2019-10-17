package com.dw2.reserva.config.swagger.validation;

import com.google.common.base.Optional;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.bean.validators.plugins.Validators;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;

import javax.validation.constraints.NotBlank;

import static springfox.bean.validators.plugins.Validators.extractAnnotation;

@Component
@Order(Validators.BEAN_VALIDATOR_PLUGIN_ORDER)
public class NotBlankPlugin implements ModelPropertyBuilderPlugin {

    @Override
    public boolean supports(DocumentationType documentationType) {
        return true;
    }

    @Override
    public void apply(ModelPropertyContext context) {
        final Optional<NotBlank> optional = extractAnnotation(context, NotBlank.class);
        if (optional.isPresent()) {
            context.getBuilder().required(true).allowEmptyValue(true);
        }
    }
}

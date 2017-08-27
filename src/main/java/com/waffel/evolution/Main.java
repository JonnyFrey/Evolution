package com.waffel.evolution;

import lombok.extern.log4j.Log4j2;
import org.jenetics.BitChromosome;
import org.jenetics.BitGene;
import org.jenetics.Genotype;
import org.jenetics.engine.Engine;
import org.jenetics.engine.EvolutionResult;
import org.jenetics.util.Factory;

/**
 * Created by Jonny on 8/23/2017.
 */
@Log4j2
public class Main {

    // 2.) Definition of the fitness function.
    private static int eval(final Genotype<BitGene> gt) {
        return gt.getChromosome()
                .as(BitChromosome.class)
                .bitCount();
    }

    public static void main(final String... args) {
        // 1.) Define the genotype (factory) suitable
        //     for the problem.
        Factory<Genotype<BitGene>> gtf =
                Genotype.of(BitChromosome.of(9, 0.5));

        LOGGER.info(gtf);

        // 3.) Create the execution environment.
        Engine<BitGene, Integer> engine = Engine
                .builder(Main::eval, gtf)
                .build();

        LOGGER.info(engine);

        // 4.) Start the execution (evolution) and
        //     collect the result.
        Genotype<BitGene> result = engine.stream()
                .limit(100)
                .map(bitGeneIntegerEvolutionResult -> {
                    LOGGER.info(bitGeneIntegerEvolutionResult.getPopulation());
                    return bitGeneIntegerEvolutionResult;
                })
                .collect(EvolutionResult.toBestGenotype());

        LOGGER.info("Hello World:\n" + result);
    }

}

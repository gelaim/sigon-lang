package br.ufsc.ine.context.plans;

import java.util.List;
import java.util.stream.Collectors;

import br.ufsc.ine.context.desires.DesiresService;
import br.ufsc.ine.context.functions.Functions;

public class PlansService {

	public static void execute(List<Plan> plans) {
		plans.forEach(p -> {
			List<String> collect = p.getPosConditions().stream().filter(c -> DesiresService.instance.haveDesire(c))
					.collect(Collectors.toList());

			if (collect.size() == p.getPosConditions().size()) {
				p.getFunctions().forEach(Functions.getInstance()::execute);
			}
		});
	}

}
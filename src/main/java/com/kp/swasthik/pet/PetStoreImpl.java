package com.kp.swasthik.pet;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.kp.swasthik.swagger.DefaultApi;
import com.kp.swasthik.swagger.models.NewPet;
import com.kp.swasthik.swagger.models.Pet;

@Service
public class PetStoreImpl implements DefaultApi {

	@Override
	public Pet addPet(@Valid NewPet pet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePet(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Pet findPetById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pet> findPets(List<String> tags, Integer limit) {
		return IntStream.range(1, 4).mapToObj(i->{
			Pet p = new Pet();
			p.setId((long)i);
			p.setName("p:"+i);
			p.setTag("tag:"+i);
			return p;
		}).collect(Collectors.toList());
	}

}

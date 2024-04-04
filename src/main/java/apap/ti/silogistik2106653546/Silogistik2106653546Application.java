package apap.ti.silogistik2106653546;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import jakarta.transaction.Transactional;

import java.util.Locale;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.github.javafaker.Faker;

import apap.ti.silogistik2106653546.dto.GudangMapper;
import apap.ti.silogistik2106653546.dto.KaryawanMapper;
import apap.ti.silogistik2106653546.dto.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106653546.dto.request.CreateKaryawanRequestDTO;
import apap.ti.silogistik2106653546.service.interfaces.GudangService;
import apap.ti.silogistik2106653546.service.interfaces.KaryawanService;

@SpringBootApplication
public class Silogistik2106653546Application {

	public static void main(String[] args) {
		SpringApplication.run(Silogistik2106653546Application.class, args);
	}

	@Bean
	@Transactional
	CommandLineRunner run(GudangService gudangService, GudangMapper gudangMapper, KaryawanMapper karyawanMapper, KaryawanService karyawanService) {
    return args -> {
        var faker = new Faker(new Locale("in-ID"));

		for (int i = 0; i < 3; i++) { // Generate and add 3 Gudangs
            var gudangDTO = new CreateGudangRequestDTO();
            gudangDTO.setNamaGudang(faker.company().name());
            gudangDTO.setAlamatGudang(faker.address().fullAddress());
            // Add other attributes if there are more for Gudang...

            // Mapping gudangDTO to gudang then save gudang to database
            var gudang = gudangMapper.createGudangRequestDTOToGudang(gudangDTO);
            gudangService.addGudang(gudang);
        }

        // Faker for Karyawan
        for (int i = 0; i < 3; i++) {
            var createGudangRequestDTO = new CreateKaryawanRequestDTO();
            createGudangRequestDTO.setNamaKaryawan(faker.name().fullName());

            // 1 for male and 2 for female
            createGudangRequestDTO.setJenisKelamin(faker.random().nextInt(1, 2));

            // Generate a random date of birth between 1970 and 2000
            createGudangRequestDTO.setTanggalLahir(faker.date().birthday(20, 50));

            var karyawan = karyawanMapper.createKaryawanRequestDTOToKaryawan(createGudangRequestDTO);
            karyawanService.addKaryawan(karyawan);
        }

	};
}
}
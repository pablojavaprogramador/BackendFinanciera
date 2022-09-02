package com.bancopoder.web.rest;

import com.bancopoder.domain.Domicilio;
import com.bancopoder.repository.DomicilioRepository;
import com.bancopoder.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.bancopoder.domain.Domicilio}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class DomicilioResource {

    private final Logger log = LoggerFactory.getLogger(DomicilioResource.class);

    private static final String ENTITY_NAME = "domicilio";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DomicilioRepository domicilioRepository;

    public DomicilioResource(DomicilioRepository domicilioRepository) {
        this.domicilioRepository = domicilioRepository;
    }

    /**
     * {@code POST  /domicilios} : Create a new domicilio.
     *
     * @param domicilio the domicilio to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new domicilio, or with status {@code 400 (Bad Request)} if the domicilio has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/domicilios")
    public ResponseEntity<Domicilio> createDomicilio(@RequestBody Domicilio domicilio) throws URISyntaxException {
        log.debug("REST request to save Domicilio : {}", domicilio);
        if (domicilio.getId() != null) {
            throw new BadRequestAlertException("A new domicilio cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Domicilio result = domicilioRepository.save(domicilio);
        return ResponseEntity
            .created(new URI("/api/domicilios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /domicilios/:id} : Updates an existing domicilio.
     *
     * @param id the id of the domicilio to save.
     * @param domicilio the domicilio to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated domicilio,
     * or with status {@code 400 (Bad Request)} if the domicilio is not valid,
     * or with status {@code 500 (Internal Server Error)} if the domicilio couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/domicilios/{id}")
    public ResponseEntity<Domicilio> updateDomicilio(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Domicilio domicilio
    ) throws URISyntaxException {
        log.debug("REST request to update Domicilio : {}, {}", id, domicilio);
        if (domicilio.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, domicilio.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!domicilioRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Domicilio result = domicilioRepository.save(domicilio);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, domicilio.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /domicilios/:id} : Partial updates given fields of an existing domicilio, field will ignore if it is null
     *
     * @param id the id of the domicilio to save.
     * @param domicilio the domicilio to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated domicilio,
     * or with status {@code 400 (Bad Request)} if the domicilio is not valid,
     * or with status {@code 404 (Not Found)} if the domicilio is not found,
     * or with status {@code 500 (Internal Server Error)} if the domicilio couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/domicilios/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Domicilio> partialUpdateDomicilio(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Domicilio domicilio
    ) throws URISyntaxException {
        log.debug("REST request to partial update Domicilio partially : {}, {}", id, domicilio);
        if (domicilio.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, domicilio.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!domicilioRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Domicilio> result = domicilioRepository
            .findById(domicilio.getId())
            .map(existingDomicilio -> {
                if (domicilio.getCalle() != null) {
                    existingDomicilio.setCalle(domicilio.getCalle());
                }
                if (domicilio.getNumeroExterior() != null) {
                    existingDomicilio.setNumeroExterior(domicilio.getNumeroExterior());
                }
                if (domicilio.getNumeroInterior() != null) {
                    existingDomicilio.setNumeroInterior(domicilio.getNumeroInterior());
                }
                if (domicilio.getCodePostal() != null) {
                    existingDomicilio.setCodePostal(domicilio.getCodePostal());
                }
                if (domicilio.getDomicilioActual() != null) {
                    existingDomicilio.setDomicilioActual(domicilio.getDomicilioActual());
                }
                if (domicilio.getManzana() != null) {
                    existingDomicilio.setManzana(domicilio.getManzana());
                }
                if (domicilio.getAndador() != null) {
                    existingDomicilio.setAndador(domicilio.getAndador());
                }
                if (domicilio.getEdificio() != null) {
                    existingDomicilio.setEdificio(domicilio.getEdificio());
                }
                if (domicilio.getFechaAntiguedad() != null) {
                    existingDomicilio.setFechaAntiguedad(domicilio.getFechaAntiguedad());
                }
                if (domicilio.getCodigoPostal() != null) {
                    existingDomicilio.setCodigoPostal(domicilio.getCodigoPostal());
                }

                return existingDomicilio;
            })
            .map(domicilioRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, domicilio.getId().toString())
        );
    }

    /**
     * {@code GET  /domicilios} : get all the domicilios.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of domicilios in body.
     */
    @GetMapping("/domicilios")
    public List<Domicilio> getAllDomicilios() {
        log.debug("REST request to get all Domicilios");
        return domicilioRepository.findAll();
    }

    /**
     * {@code GET  /domicilios/:id} : get the "id" domicilio.
     *
     * @param id the id of the domicilio to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the domicilio, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/domicilios/{id}")
    public ResponseEntity<Domicilio> getDomicilio(@PathVariable Long id) {
        log.debug("REST request to get Domicilio : {}", id);
        Optional<Domicilio> domicilio = domicilioRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(domicilio);
    }

    /**
     * {@code DELETE  /domicilios/:id} : delete the "id" domicilio.
     *
     * @param id the id of the domicilio to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/domicilios/{id}")
    public ResponseEntity<Void> deleteDomicilio(@PathVariable Long id) {
        log.debug("REST request to delete Domicilio : {}", id);
        domicilioRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}

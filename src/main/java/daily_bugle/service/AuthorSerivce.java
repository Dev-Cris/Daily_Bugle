package daily_bugle.service;

import daily_bugle.domain.Author;
import daily_bugle.dto.AuthorCreateUpdateCommand;
import daily_bugle.dto.AuthorInfo;
import daily_bugle.exceptionHandling.AuthorNotFoundException;
import daily_bugle.repository.AuthorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AuthorSerivce {

    private final AuthorRepository authorRepository;

    private final ModelMapper modelMapper;

    public AuthorSerivce(AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    public AuthorInfo saveAuthor(AuthorCreateUpdateCommand command){
        Author newAuthor = modelMapper.map(command, Author.class);
        Author saved = authorRepository.save(newAuthor);
        return modelMapper.map(saved, AuthorInfo.class);
    }

    public Author findAuthorById(Long authorId) {
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        return  authorOptional.orElseThrow(() -> new AuthorNotFoundException(authorId));
    }
}

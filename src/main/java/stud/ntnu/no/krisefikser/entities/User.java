package stud.ntnu.no.krisefikser.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements UserDetails {
  /**
   * Unique identifier for the user.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private Long id;

  /**
   * Encrypted password of the user.
   */
  @Column(nullable = false)
  private String password;

  /**
   * Username of the user.
   * Must be unique and not null.
   */
  @Column(unique = true, nullable = false)
  private String email;

  public String getUsername() {
    return email;
  }
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    //TODO implement getauthorities correctly
    return List.of(new SimpleGrantedAuthority("USER"));
  }


  /**
   * Indicates whether the user's account has expired.
   * Always returns {@code true}, meaning accounts do not expire.
   *
   * @return {@code true} since the account never expires.
   */
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  /**
   * Indicates whether the user's account is locked.
   * Always returns {@code true}, meaning accounts are never locked.
   *
   * @return {@code true} since the account is never locked.
   */
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  /**
   * Indicates whether the user's credentials (password) have expired.
   * Always returns {@code true}, meaning credentials never expire.
   *
   * @return {@code true} since credentials never expire.
   */
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  /**
   * Indicates whether the user is enabled.
   * Always returns {@code true}, meaning all users are enabled.
   *
   * @return {@code true} since all users are enabled by default.
   */
  @Override
  public boolean isEnabled() {
    return true;
  }
}
